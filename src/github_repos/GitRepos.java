package github_repos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;


public class GitRepos {
    
    public JSONArray getJSON() throws IOException, JSONException{

        String url = "https://api.github.com/users/wkeilsohn/repos?per_page=100"; // Indicate the repo number.
        URL get_url = new URL(url);

        HttpURLConnection con = (HttpURLConnection) get_url.openConnection();
        con.setRequestProperty("Accept", "application/vnd.github.v3+json");

        con.setRequestMethod("GET");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        String input_line;
        String reps = new String();
         while ((input_line = in.readLine()) != null){
            reps = reps.concat(input_line);
         }

        in.close();
        con.disconnect();
   
        JSONArray json = new JSONArray(reps);
        return json;
    }
}