package com.ml.blog.lucene;

import com.ml.blog.util.DateUtils;
import com.ml.blog.vo.ArticleSearchVO;
import com.ml.blog.vo.ArticleVO;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Paths;
import java.util.Date;

/**
 * @author Mr.ml
 * @date 2021/1/15
 */
@Component
public class ArticleIndex {

    public static final String directoryPath = System.getProperty("user.home");

    /**
     * 索引库文件存放的目录位置
     */
    private Directory directory;

    /**
     * 获取IndexWriter对象
     *
     * @return
     */
    public IndexWriter getIndexWriter() throws Exception {
        // 1.指定lucene索引库的地址
        directory = FSDirectory.open(Paths.get(directoryPath + File.separator + "dir"));
        // 2.指定写入器的分词类型，使用IKAnalyzer分词器
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new IKAnalyzer());
        // 3.创建IndexWriter写入器对象
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        return indexWriter;
    }

    /**
     * 添加博客索引
     *
     * @param articleVO
     */
    public void addIndex(ArticleVO articleVO) throws Exception {
        // 获取indexWriter对象
        IndexWriter indexWriter = getIndexWriter();
        // 创建文档对象
        Document doc = new Document();
        // 博客id 标题 描述 内容 作者 修改时间 类型 首图 访问量
        // 参数1：域名字段名称  参数2：字段值  参数3：是否存储
        // 博客编号
        doc.add(new StringField("articleId", String.valueOf(articleVO.getArticleId()), Field.Store.YES));
        // 博客修改时间
        doc.add(new StringField("updateTime", DateUtils.formatDate(new Date(), "yyyy-MM-dd"), Field.Store.YES));
        // 博客标题
        doc.add(new StringField("title", articleVO.getTitle(), Field.Store.YES));
        // 作者
        doc.add(new StringField("author", articleVO.getAuthor(), Field.Store.YES));
        // 博客描述
        doc.add(new TextField("description", articleVO.getDescription(), Field.Store.YES));
        // 博客内容
        doc.add(new TextField("content", articleVO.getContent(), Field.Store.YES));
        // 首图
        doc.add(new StringField("firstPicture", articleVO.getFirstPicture(), Field.Store.YES));
        // 访问量
        doc.add(new StringField("views", String.valueOf(articleVO.getViews()), Field.Store.YES));
        // 将文档写入到索引库
        indexWriter.addDocument(doc);
        // 关闭资源
        indexWriter.close();
    }

    /**
     * 根据用户输入内容进行索引查询
     *
     * @param keyword
     * @return
     * @throws Exception
     */
    public List<ArticleSearchVO> searchIndex(String keyword) throws Exception {
        // 创建集合保存查询的结果
        List<ArticleSearchVO> list = new ArrayList<>();
        // 1.读取指定lucene索引库的地址
        directory = FSDirectory.open(Paths.get(directoryPath + File.separator + "dir"));
        // 2.创建IndexReader对象并读取文件
        IndexReader indexReader = DirectoryReader.open(directory);
        // 3.创建IndexSearcher查询对象，并将IndexReader传入
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        // 4.创建IK分词器对象
        IKAnalyzer analyzer = new IKAnalyzer();
        // 5.创建QueryParser对象，指定查询范围及分词器
        // 标题查询（参数1：域名（必须与创建索引时的域名一致） 参数2：分词器对象）
        QueryParser parserTitle = new QueryParser("title", analyzer);
        // 根据用户输入的内容（关键词）进行分词操作，得到Query对象
        Query queryTitle = parserTitle.parse(keyword);
        // 内容查询（参数1：域名（必须与创建索引时的域名一致） 参数2：分词器对象）
        QueryParser parserContent = new QueryParser("content", analyzer);
        // 根据用户输入的内容（关键词）进行分词操作，得到Query对象
        Query queryContent = parserContent.parse(keyword);
        // 6.将上面的标题及内容条件进行封装，创建条件查询对象
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        // SHOULD：或者
        builder.add(queryTitle, BooleanClause.Occur.SHOULD);
        builder.add(queryContent, BooleanClause.Occur.SHOULD);
        // 获取文档列表,指定查询的前100条记录
        TopDocs topDocs = indexSearcher.search(builder.build(), 100);
        // 得到查询内容最高分的结果
        QueryScorer scorer = new QueryScorer(queryTitle);
        // 创建SimpleSpanFragmenter模板对象
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
        // 创建SimpleHTMLFormatter对象
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
        // 高亮显示
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
        // 将高亮显示的内容放到模板中
        highlighter.setTextFragmenter(fragmenter);
        // 循环遍历文档列表
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            // 获取每一个文档对象
            Document doc = indexSearcher.doc(scoreDoc.doc);
            // 创建博客对象
            ArticleSearchVO article = new ArticleSearchVO();
            // 取出文档id
            article.setArticleId(Integer.valueOf(doc.get("articleId")));
            // 取出作者
            article.setAuthor(doc.get("author"));
            // 取出更新日期
            article.setUpdateTime(DateUtils.formatString(doc.get("updateTime"), "yyyy-MM-dd"));
            // 取出首图
            article.setFirstPicture(doc.get("firstPicture"));
            // 取出访问量
            article.setViews(Integer.valueOf(doc.get("views")));
            // 取出标题
            String title = doc.get("title");
            // 取出描述
            article.setDescription(doc.get("description"));
            // 取出内容
            String content = doc.get("content");
            // 如果标题内容不为空
            if (title != null) {
                // 创建TokenStream
                TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(title));
                // 得到最佳匹配的标题内容
                String hTitle = highlighter.getBestFragment(tokenStream, title);
                // 如果从lucene取出的标题为空，则显示普通效果
                if (StringUtils.isEmpty(hTitle)) {
                    article.setTitle(title);
                } else {
                    // 高亮显示
                    article.setTitle(hTitle);
                }
            }

            // 如果内容不为空
            if (content != null) {
                // 对内容进行分词
                TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(content));
                // 获取最佳匹配的内容
                String hContent = highlighter.getBestFragment(tokenStream, content);
                // 如果最佳匹配的数据为空，则按默认的样式显示（在页面中显示黑体字）
                if (StringUtils.isEmpty(hContent)) {
                    // 如果博客内容长度小于200，则直接显示
                    if (content.length() <= 200) {
                        article.setContent(content);
                    } else {
                        // 如果博客内容长度大于200，则截取前200个文字
                        article.setContent(content.substring(0, 200));
                    }
                } else {
                    // 高亮显示
                    article.setContent(hContent);
                }
            }

            // 将博客信息添加到集合中
            list.add(article);
        }

        return list;
    }


    /**
     * 删除博客的索引
     *
     * @param articleId
     * @throws Exception
     */
    public void deleteIndex(String articleId) throws Exception {
        // 获取IndexWriter对象
        IndexWriter indexWriter = getIndexWriter();
        // 根据索引id删除文档
        indexWriter.deleteDocuments(new Term("articleId", articleId));
        // 强制删除
        indexWriter.forceMergeDeletes();
        // 提交
        indexWriter.commit();
        // 关闭资源
        indexWriter.close();
    }

    /**
     * 修改索引
     *
     * @param articleVO
     */
    public void updateIndex(ArticleVO articleVO) throws Exception {
        // 获取IndexWriter对象
        IndexWriter indexWriter = getIndexWriter();
        // 创建文档对象
        Document doc = new Document();
        // 在lucene中需要存储四个内容，分别是博客id，博客发布时间，博客标题，博客内容
        // 参数1：域名字段名称  参数2：字段值  参数3：是否存储
        // 博客编号
        doc.add(new StringField("articleId", String.valueOf(articleVO.getArticleId()), Field.Store.YES));
        // 博客修改时间
        doc.add(new StringField("updateTime", DateUtils.formatDate(new Date(), "yyyy-MM-dd"), Field.Store.YES));
        // 博客标题
        doc.add(new StringField("title", articleVO.getTitle(), Field.Store.YES));
        // 作者
        doc.add(new StringField("author", articleVO.getAuthor(), Field.Store.YES));
        // 博客描述
        doc.add(new TextField("description", articleVO.getDescription(), Field.Store.YES));
        // 博客内容
        doc.add(new TextField("content", articleVO.getContent(), Field.Store.YES));
        // 首图
        doc.add(new StringField("firstPicture", articleVO.getFirstPicture(), Field.Store.YES));
        // 访问量
        doc.add(new StringField("views", String.valueOf(articleVO.getViews()), Field.Store.YES));
        // 博客内容
        //doc.add(new TextField("content", articleVO.getContentNoTag(), Field.Store.YES));
        // 将文档写入到索引库
        indexWriter.updateDocument(new Term("articleId", String.valueOf(articleVO.getArticleId())), doc);
        // 关闭资源
        indexWriter.close();
    }

}
