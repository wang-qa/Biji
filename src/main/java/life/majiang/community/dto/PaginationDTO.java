package life.majiang.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious; // 是否有向前按钮
    private boolean showFirstPage; // 首页按钮
    private boolean showNext; // 下一页按钮
    private boolean showEndPage; // 尾页按钮

    private Integer Page; // 当前页

    private List<Integer> pages = new ArrayList<>(); // 总页码
    private Integer page;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        Integer totalPage;
        // 计算总页数  totalCount / size
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        // 页码异常处理
        if (page < 1) {//页码小于1
            page = 1;
        }
        if (page > totalPage) {//页码大于page
            page = totalPage;
        }

        this.page = page;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }

            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        // 是否展示上一页
        // 当前为第一页不展示
        showPrevious = page != 1;

        // 是否展示下一页
        // 当前为最后一页不展示
        showNext = page != totalPage;

        // 是否展示第一页
        // 当前为不包含一页不展示
        showFirstPage = !pages.contains(1);

        // 是否展示最后一页
        // 当前不包含最后一页不展示
        showEndPage = !pages.contains(totalPage);
    }
}
