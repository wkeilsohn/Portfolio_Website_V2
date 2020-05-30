package github_repos;

import java.util.Vector;

public class gitrepo_main {
    
    public String HTMLPrinter() {
        try {
            GitRepos git = new GitRepos();
            ProcessRepos pros = new ProcessRepos();
            html_maker html = new html_maker();
            Vector repos = pros.JVec(git.getJSON());
            String table = html.makeHTML(repos);
            return table;
        } catch (Exception i) {
        	String error = "500 Error on Git Java";
            return error;
        }
    }
    
    public void main(String[] args) {
    	System.out.println(HTMLPrinter());
    }
    
}