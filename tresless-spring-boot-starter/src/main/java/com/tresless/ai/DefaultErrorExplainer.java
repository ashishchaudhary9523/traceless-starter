package com.tresless.ai;

import com.tresless.core.ai.ErrorExplainer;
import com.tresless.core.error.ErrorContext;

public class DefaultErrorExplainer implements ErrorExplainer {
    @Override
    public String explain(ErrorContext errorContext) {
        return "This request failed due to server-side issue. Please try again later.";
    }
}
