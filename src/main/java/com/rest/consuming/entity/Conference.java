package com.rest.consuming.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Conference {

  private long id;
  private String subject;
  private String readerName;

  public Conference(long id, String subject, String readerName) {
    this.id = id;
    this.subject = subject;
    this.readerName = readerName;
  }

  public Conference() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getReaderName() {
    return readerName;
  }

  public void setReaderName(String readerName) {
    this.readerName = readerName;
  }

  @Override
  public String toString() {
    return "Conference{" +
        "id=" + id +
        ", subject='" + subject + '\'' +
        ", readerName='" + readerName + '\'' +
        '}';
  }
}
