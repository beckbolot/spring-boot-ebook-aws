package com.beck.springbootebookaws.api.payload.book;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseView {

    List<BookResponse> bookResponses;
}
