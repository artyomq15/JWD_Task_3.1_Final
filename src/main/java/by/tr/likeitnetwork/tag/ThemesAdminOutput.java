package by.tr.likeitnetwork.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class ThemesAdminOutput extends TagSupport {
    private String action;
    private List<List<Object>> themes;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<List<Object>> getThemes() {
        return themes;
    }

    public void setThemes(List<List<Object>> themes) {
        this.themes = themes;
    }

    @Override
    public int doStartTag() throws JspException {
        int prev = 0;
        JspWriter out = pageContext.getOut();
        try {
            for (List<Object> list : themes) {
                int id = (Integer)list.get(0);
                if (prev == 0){
                    out.write("<div class=\"card themes_info\">");
                    prev = id;
                    out.write("<div class=\"themes_info-id\">#" + prev + "</div>");
                    out.write("<form action=\"/NetworkController\" method=\"post\">\n" +
                            "                            <input type=\"hidden\" name=\"command\" value=\"change_themes\">\n" +
                            "                            <input type=\"hidden\" name=\"action\" value=\"change\">\n" +
                            "                            <input type=\"hidden\" name=\"theme_id\" value=\"" + id + "\">");
                }
                if (prev == id){
                    out.write("<div class=\"themes_info-language\">" + list.get(2) + "</div>\n" +
                            "                            <input type=\"text\" name=\"" + list.get(2) + "\" value=\"" + list.get(3) + "\" pattern=\"^[A-Za-zА-Яа-яІіўЁё'-]{2,50}$\"/>");
                }
                if (prev < id){
                    out.write("<input type=\"submit\" class=\"save_button\" value=\"SAVE\"/>\n" +
                            "                        </form>\n" +
                            "                        <a href=\"/NetworkController?command=show_hide_theme&action=" + action + "&theme_id=" + prev + "\" class=\"hide_show_button " + action + "\">" + action.toUpperCase() + "</a>\n" +
                            "                    </div>\n" +
                            "                    <div class=\"card themes_info\">\n" +
                            "                        <div class=\"themes_info-id\">#" + id + "</div>\n" +
                            "                        <form action=\"/NetworkController\" method=\"post\">\n" +
                            "                            <input type=\"hidden\" name=\"command\" value=\"change_themes\">\n" +
                            "                            <input type=\"hidden\" name=\"action\" value=\"change\">\n" +
                            "                            <input type=\"hidden\" name=\"theme_id\" value=\"" + id + "\">\n" +
                            "                            <div class=\"themes_info-language\">" + list.get(2) + "</div>\n" +
                            "                            <input type=\"text\" name=\"" + list.get(2) + "\" value=\"" + list.get(3) + "\" pattern=\"^[A-Za-zА-Яа-яІіўЁё'-]{2,50}$\"/>");
                    prev = id;
                }
            }
            if (prev > 0){
                out.write("<input type=\"submit\" class=\"save_button\" value=\"SAVE\"/>\n" +
                        "                        </form>\n" +
                        "                        <a href=\"/NetworkController?command=show_hide_theme&action=" + action + "&theme_id=" + prev + "\" class=\"hide_show_button " + action + "\">" + action.toUpperCase() + "</a>\n" +
                        "                    </div>");
            }
        } catch (IOException ex) {
            throw new JspException(ex.getMessage());
        }


        return SKIP_BODY;
    }


}
