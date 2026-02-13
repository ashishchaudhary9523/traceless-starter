package com.tresless.core.detection;

import com.tresless.core.signal.Signal;
import java.util.Optional;

public interface FailureDetector {
    Optional<Failure> detect(Signal signal);
}
