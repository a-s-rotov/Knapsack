package com.mobiquity.exception;

public class AlgorithmException extends RuntimeException {

  public AlgorithmException(String message, Exception e) {
    super(message, e);
  }

  public AlgorithmException(String message) {
    super(message);
  }
}
