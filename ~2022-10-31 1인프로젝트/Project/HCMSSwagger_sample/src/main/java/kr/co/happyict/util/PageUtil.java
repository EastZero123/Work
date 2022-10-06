package kr.co.happyict.util;

public class PageUtil {

  /**
   * 페이지수를 구한다.
   *
   * @param numberPage     int 한페이지에서 보여주는 row수
   * @param totalDataCount int 전체데이터 수
   * @return int
   */
  public static int getPageCount(int numberPage, int totalDataCount) {
    return (totalDataCount % numberPage == 0) ? totalDataCount / numberPage
        : totalDataCount / numberPage + 1;
  }

  /**
   * 페이징 인덱스 리스트를 구한다.
   *
   * @param currentPage int 현재페이지 넘버
   * @param totalPage   int 전체페이지 수
   * @param numberPage  int 한페이지에 보여질 ROW수
   * @param listUrl     String URL주소
   * @return String     페이징HTML
   */
  public static String pageIndexList(int currentPage, int totalPage, int numberPage, String listUrl,
      int pageBlock) {
    int currentPageSetUp;
    int n;
    int page;

    StringBuilder strList = new StringBuilder();

    if (currentPage == 0) {
      return "";
    }

    currentPageSetUp = (currentPage / pageBlock) * pageBlock;

    if (currentPage % pageBlock == 0) {
      currentPageSetUp = currentPageSetUp - pageBlock;
    }

    if (currentPage > pageBlock) {
      strList.append(
          "<a href=\"javascript:goSearch(1)\" <i class=\"mdi mdi-page-first\"><span>처음으로</span></a>\n");
    }

    n = ((currentPage - 1) / pageBlock) * pageBlock;

    if (n == 0) {
      n = 1;
    }

    if (totalPage > pageBlock && currentPageSetUp > 0) {
      strList.append("<button class=\"btn-pagePrev\" onClick=\"javascript:goSearch(").append(n)
          .append(");\"><span class=\"blind\">이전 페이지</span></button>");
    } else {
      strList.append(
          "<button class=\"btn-pagePrev\" onClick=\"javascript:goSearch(1);\"><span class=\"blind\">이전 페이지</span></button>");
    }

    page = currentPageSetUp + 1;

    if (totalPage > 0) {
      while ((page <= totalPage) && (page <= currentPageSetUp + pageBlock)) {
        if (page == currentPage) {
          strList.append("<a href=\"#\" class=\"current\" title=\"현재 페이지\">").append(page)
              .append("</a>\n");
        } else {
          strList.append("<a href=\"javascript:goSearch(").append(page).append(");\">").append(page)
              .append("</a>\n");
        }

        page++;
      }
    } else {
      strList.append("<a href=\"#\" class=\"current\" title=\"현재 페이지\">").append(page)
          .append("</a>");
    }

    if (totalPage < 1) {
      totalPage = 1;
    }

    if (totalPage - currentPageSetUp > pageBlock) {
      n = (((currentPage - 1) + pageBlock) / pageBlock) * pageBlock + 1;
      strList.append("<button class=\"btn-pageNext\" onClick=\"javascript:goSearch(").append(n)
          .append(");\"><span class=\"blind\">다음 페이지</span></button>\n");
    } else {
      strList.append("<button class=\"btn-pageNext\" onClick=\"javascript:goSearch(")
          .append(totalPage).append(");\"><span class=\"blind\">다음 페이지</span></button>\n");
    }

    if (currentPage < totalPage - pageBlock) {
      strList.append("<button class=\"btn-pageLast m-hide\" onClick=\"javascript:goSearch(")
          .append(totalPage).append(");\"><span class=\"blind\">마지막 페이지</span></button>");
    }

    return strList.toString();
  }

  public static String pageIndexListAjax(int currentPage, int totalPage, int numberPage,
      String listUrl, int pageBlock) {
    int currentPageSetUp;
    int n;
    int page;

    StringBuilder strList = new StringBuilder();

    if (currentPage == 0) {
      return "";
    }

    currentPageSetUp = (currentPage / pageBlock) * pageBlock;

    if (currentPage % pageBlock == 0) {
      currentPageSetUp = currentPageSetUp - pageBlock;
    }

    if (currentPage > pageBlock) {
      strList.append(
          "<button class=\"btn-pageFirst m-hide\" onClick=\"javascript:goSearchAjax(1);\"><span class=\"blind\">처음으로</span></button>\n");
    }

    n = ((currentPage - 1) / pageBlock) * pageBlock;

    if (n == 0) {
      n = 1;
    }
    	
    if (totalPage > pageBlock && currentPageSetUp > 0) {
      strList.append("<button class=\"btn-pagePrev\" onClick=\"javascript:goSearchAjax(").append(n)
          .append(");\"><span class=\"blind\">이전 페이지</span></button>");
    } else {
      strList.append(
          "<button class=\"btn-pagePrev\" onClick=\"javascript:goSearchAjax(1);\"><span class=\"blind\">이전 페이지</span></button>");
    }

    page = currentPageSetUp + 1;

    if (totalPage > 0) {
      while ((page <= totalPage) && (page <= currentPageSetUp + pageBlock)) {
        if (page == currentPage) {
          strList.append("<a href=\"#\" class=\"current\" title=\"현재 페이지\">").append(page)
              .append("</a>\n");
        } else {
          strList.append("<a href=\"javascript:goSearchAjax(").append(page).append(");\">")
              .append(page).append("</a>\n");
        }

        page++;
      }
    } else {
      strList.append("<a href=\"#\" class=\"current\" title=\"현재 페이지\">").append(page)
          .append("</a>");
    }

    if (totalPage < 1) {
      totalPage = 1;
    }

    if (totalPage - currentPageSetUp > pageBlock) {
      n = (((currentPage - 1) + pageBlock) / pageBlock) * pageBlock + 1;
      strList.append("<button class=\"btn-pageNext\" onClick=\"javascript:goSearchAjax(").append(n)
          .append(");\"><span class=\"blind\">다음 페이지</span></button>\n");
    } else {
      strList.append("<button class=\"btn-pageNext\" onClick=\"javascript:goSearchAjax(")
          .append(totalPage).append(");\"><span class=\"blind\">다음 페이지</span></button>\n");
    }

    if (currentPage < totalPage - pageBlock) {
      strList.append("<button class=\"btn-pageLast m-hide\" onClick=\"javascript:goSearchAjax(")
          .append(totalPage).append(");\"><span class=\"blind\">마지막 페이지</span></button>");
    }

    return strList.toString();
  }

  /**
   * 페이징 인덱스 리스트를 구한다. (ajax 전용)
   *
   * @param currentPage int 현재페이지 넘버
   * @param totalPage   int 전체페이지 수
   * @param numberPage  int 한페이지에 보여질 ROW수
   */
  public static String pageIndexListAjax(int currentPage, int totalPage, int numberPage,
      int start) {
    int numPerBlock = 10;   // 리스트에 나타낼 페이지 수
    int currentPageSetUp;
    int n;
    int page;

    StringBuilder strList = new StringBuilder();

    if (currentPage == 0) {
      return "";
    }

    // 표시할 첫 페이지
    currentPageSetUp = (currentPage / numPerBlock) * numPerBlock;

    if (currentPage % numPerBlock == 0) {
      currentPageSetUp = currentPageSetUp - numPerBlock;
    }

    // 시작페이지
    strList.append(
        "<a href=\"javascript:fnQnaCommentSelect(1)\" class=\"direction first\" title=\"처음으로\"><span class=\"blind\">First</span></a>");

    // Prev
    n = ((currentPage - 1) / numPerBlock) * numPerBlock;

    if (n == 0) {
      n = 1;
    }

    if (totalPage > numPerBlock && currentPageSetUp > 0) {
      strList.append("<a href=\"javascript:fnQnaCommentSelect(").append(n).append(
          ")\" class=\"direction prev\" title=\"이전\"><span class=\"blind\">Prev</span></a>");
    } else {
      strList.append(
          "<a href=\"javascript:fnQnaCommentSelect(1)\" class=\"direction prev\" title=\"이전\"><span class=\"blind\">Prev</span></a>");
    }

    // 바로가기 페이지 구현
    page = currentPageSetUp + 1;

    while ((page <= totalPage) && (page <= currentPageSetUp + numPerBlock)) {
      if (page == currentPage) {
        strList.append("<strong>").append(page).append("</strong>");
      } else {
        strList.append("<a href=\"javascript:fnQnaCommentSelect(").append(page).append(")\">")
            .append(page).append("</a>");
      }

      page++;
    }

    // NEXT
    if (totalPage < 1) {
      totalPage = 1;
    }

    n = (((currentPage - 1) + numPerBlock) / numPerBlock) * numPerBlock + 1;

    if (totalPage - currentPageSetUp > numPerBlock) {
      strList.append("<a href=\"javascript:fnQnaCommentSelect(").append(n).append(
          ")\" class=\"direction next\" title=\"다음\"><span class=\"blind\">Next</span></a>");
    } else {
      strList.append("<a href=\"javascript:fnQnaCommentSelect(").append(totalPage).append(
          ")\" class=\"direction next\" title=\"다음\"><span class=\"blind\">Next</span></a>");
    }

    // 끝 페이지
    strList.append("<a href=\"javascript:fnQnaCommentSelect(").append(totalPage).append(
        ")\" class=\"direction last\" title=\"마지막으로\"><span class=\"blind\">Last</span></a>");

    return strList.toString();
  }
}
