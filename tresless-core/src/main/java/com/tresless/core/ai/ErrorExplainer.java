package com.tresless.core.ai;

import com.tresless.core.error.ErrorContext;

public interface ErrorExplainer {
    String explain(ErrorContext errorContext);
}
