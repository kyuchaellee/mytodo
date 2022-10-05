package org.zerock.mytodo.dto;

import lombok.Getter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;

@Getter
@ToString
public class PageResponseDTO {

    private int start,end;
    private boolean prev,next;
    private int total;

    private String link;
    private PageRequestDTO pageRequestDTO;

    public PageResponseDTO(PageRequestDTO pageRequestDTO, int total){

        this.pageRequestDTO = pageRequestDTO;
        this.total = total;
        //현재 페이지 번호
        int currentPage = pageRequestDTO.getPage();

        int size = pageRequestDTO.getSize();

        //시작 페이지 계산
        this.start = (int)(Math.floor( (currentPage -1)/10.0 ) * 10 ) +1;

        //마지막 페이지 계산 123/10.0 = 12.3


        if( (this.start + 9) * size < total ){
            this.end = this.start + 9;
        }else {
            this.end =   (int)(Math.ceil(total/(double)size));
        }

        this.prev = this.start != 1;

        this.next =  (this.end * size) < total;

    }

    public String getLink(){
        String keyword = this.pageRequestDTO.getKeyword();
        LocalDate from = this.pageRequestDTO.getFrom();
        LocalDate to = this.pageRequestDTO.getTo();
        Boolean complete = this.pageRequestDTO.isComplete();

        StringBuilder builder = new StringBuilder();

        if(keyword != null){
            try {
                builder.append("&keyword="+ URLEncoder.encode(keyword,"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        if(from != null && to != null){
            builder.append("&from="+from.toString());
            builder.append("&to="+to.toString());
        }

        return builder.toString();

    }

}