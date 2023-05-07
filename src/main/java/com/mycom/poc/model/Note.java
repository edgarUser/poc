package com.mycom.poc.model;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Note {

  private String id;
  private String description;
  private Date created;
}
