package github_repos;

import org.json.JSONObject;

import org.json.JSONArray;
import java.util.Vector;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProcessRepos {

    public String partString(String st){
        String st_p1 = st.substring(0, 10);
        String st_p2 = st.substring(11, 20);
        String st_pf = st_p1.concat(":");
        st_pf = st_pf.concat(st_p2);
        return st_pf;
    }

    public Date stringDate(String st){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        try{
            Date date = format.parse(st);
            return date;
        } catch (Exception e){
            Date now = new Date();
            return now; // Not ideal, but you should at least see it.
        }
    }
    
    public Vector JVec(JSONArray j) {

        Vector<Vector> j_out_vector = new Vector<Vector>();

    //    System.out.println(j.length());

        for (var i = 0; i < j.length(); i++){
            JSONObject obj = j.getJSONObject(i);

            Vector obv = new Vector();
            
            String prod_name = obj.getString("name");
            String prod_url = obj.getString("url");
            String prod_create = obj.getString("created_at");
            String prod_update = obj.getString("updated_at");

            Date prod_cd = stringDate(partString(prod_create));
            Date prod_ud = stringDate(partString(prod_update));
            
            obv.add(prod_name);
            obv.add(prod_url);
            obv.add(prod_cd);
            obv.add(prod_ud);

            if (i == 0){
                j_out_vector.add(obv);
            }
            int j_size = j_out_vector.size();

            for (var z = 0; z < j_size; z++){
                Vector tmp = j_out_vector.get(z);
                Date d1 = (Date) tmp.get(3); ///ugh...

                if (prod_ud.before(d1)) {
                    j_out_vector.insertElementAt(obv, z);
                    break;
                }else if (z == (j_size -1)){
                    j_out_vector.add(obv);
                    break; //Redundant, but just to be safe.
                }else if (prod_ud.equals(d1)){
                    j_out_vector.insertElementAt(obv, z);
                    break;
                }else if (prod_ud.after(d1)){
                    if((z < (j_size - 1))){
                    Vector tmp2 = j_out_vector.get(z + 1);
                    Date d2 = (Date) tmp2.get(3);
                        if(prod_ud.before(d2)){
                            j_out_vector.insertElementAt(obv, z + 1);
                            break;
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                } else {
                    try{
                        j_out_vector.insertElementAt(obv, z + 1);
                    } catch (Exception e){
                        j_out_vector.add(obv);
                    }
                    break;
                }
            }
        }
        return j_out_vector;
    }

}