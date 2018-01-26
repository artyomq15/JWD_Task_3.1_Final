package by.tr.likeitnetwork.tag;

import by.tr.likeitnetwork.controller.constant.AttributeKey;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PaginationOutput extends TagSupport {
    private static final String SERVLET_NAME = "NetworkController";
    private static final String SERVLET_DELIMITER = "?";
    private static final String PARAMETER_DELIMITER = "&";
    private static final String EQUALS = "=";

    private String command;
    private String action;
    private String expression;
    private Integer themeId;
    private Integer pageNumber;
    private Integer listSize;
    private Integer count;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getListSize() {
        return listSize;
    }

    public void setListSize(Integer listSize) {
        this.listSize = listSize;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        StringBuilder query = new StringBuilder();
        query.append(SERVLET_NAME).append(SERVLET_DELIMITER).append(AttributeKey.COMMAND).append(EQUALS).append(command);
        if (action != null){
            query.append(PARAMETER_DELIMITER).append(AttributeKey.ACTION).append(EQUALS).append(action);
        } else if (expression != null){
            query.append(PARAMETER_DELIMITER).append(AttributeKey.EXPRESSION).append(EQUALS).append(expression);
        } else if (themeId != null) {
            query.append(PARAMETER_DELIMITER).append(AttributeKey.THEME_ID).append(EQUALS).append(themeId);
        }
        query.append(PARAMETER_DELIMITER).append(AttributeKey.COUNT).append(EQUALS).append(count);
        query.append(PARAMETER_DELIMITER).append(AttributeKey.PAGE_NUMBER).append(EQUALS);

        try {
            if (pageNumber != 1) {
                out.write("<a href=\"" + query + 1 + "\">1</a>\n");
                out.write("<a href=\"" + query + (pageNumber - 1) + "\"> <img src=\"../../img/previous.png\" ></a>\n");
            }
            if (listSize.equals(count)){
                out.write("<a href=\"" + query + (pageNumber + 1) + "\"><img src=\"../../img/next.png\" ></a>\n");
            }


        } catch (IOException ex) {
            throw new JspException(ex.getMessage());
        }


        return SKIP_BODY;
    }
}
