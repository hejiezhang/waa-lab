package edu.miu.waa_lab.entity.dto;

import lombok.Data;

@Data
public class PostDto {

    long id;
    String title;
    String content;
    String author;
}
