package github_repos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class html_maker {

    DateFormat date_format = new SimpleDateFormat("mm-dd-yyyy");

    public String linkMaker(String ttl, String url){
        String link_str = new StringBuilder().append("<a class='gitrepo_id' href = '").append(url).append("'>").append(ttl).append("</a>").toString();
        String link_column = new StringBuilder().append("<td>").append(link_str).append("</td>").toString();
        return link_column;
    }

    public String dateTracker(String upd, String crd){
//        String create_date = new StringBuilder().append("Created: ").append(crd).toString();
//        String update_date = new StringBuilder().append("Last Updated: ").append(upd).toString();
        String date_column = new StringBuilder().append("<td>").append(crd).append("</td><td>").append(upd).append("</td>").toString();
        return date_column;
    }

    public String makeTableEntry(String id, String dates){
        String row = new StringBuilder().append("<tr>").append(id).append(dates).append("</tr>").toString();
        return row;
    }
    
    public String makeTableContents(Vector j_v){

        String table_contents = "";

        for (int i = 0; i < j_v.size(); i++){

            Vector<String> web = new Vector<String>();

            Vector part_i = (Vector) j_v.get(i);

            String title = (String) part_i.get(0);
            String url = (String) part_i.get(1);
            String updated = date_format.format(part_i.get(2));
            String created = date_format.format(part_i.get(3));

            String c1 = linkMaker(title, url);
            String c2 = dateTracker(updated, created);
            String row = makeTableEntry(c1, c2);

            table_contents = new StringBuilder().append(row).append(table_contents).toString();
        }
        return table_contents;
    }

    public String makeHTML(Vector j_reps){
        String table_head = "<table><tr><th>Project Name</th><th>Created:</th><th>Last Updated</th><tr>";
        String table_footing = "</table>";
        String body = makeTableContents(j_reps);
        String table = new StringBuilder().append(table_head).append(body).append(table_footing).toString();
        return table;
    }
}