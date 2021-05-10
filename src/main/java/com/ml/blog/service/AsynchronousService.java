package com.ml.blog.service;

import java.util.concurrent.Future;

public interface AsynchronousService {

    Future updateViews(Integer articleId);

}
