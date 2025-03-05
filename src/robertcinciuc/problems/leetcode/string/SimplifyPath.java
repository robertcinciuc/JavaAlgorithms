package robertcinciuc.problems.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class SimplifyPath {

    private static class Tuple{
        String directory;
        int end;

        public Tuple(String directory, int end){
            this.directory = directory;
            this.end = end;
        }
    }

    public String simplifyPath(String path) {
        List<String> cleanedDirectories = getCleanedDirectories(path);
        List<String> filteredDirectories = getFilteredDirectories(cleanedDirectories);
        StringBuilder sb = getSimplifiedString(filteredDirectories);
        return sb.toString();
    }

    private StringBuilder getSimplifiedString(List<String> filteredDirectories) {
        StringBuilder sb = new StringBuilder();
        if(filteredDirectories.isEmpty()){
            sb.append("/");
            return sb;
        }

        for(String directory: filteredDirectories){
            sb.append("/");
            sb.append(directory);
        }

        return sb;
    }

    private List<String> getFilteredDirectories(List<String> cleanedDirectories) {
        List<String> filteredDirectories = new ArrayList<>();
        for(String directory: cleanedDirectories){
            if(directory.equals(".")){
                continue;
            }else if(directory.equals("..")){
                if(filteredDirectories.size() > 0){
                    filteredDirectories.remove(filteredDirectories.size() - 1);
                }
            }else{
                filteredDirectories.add(directory);
            }
        }
        return filteredDirectories;
    }

    private List<String> getCleanedDirectories(String path) {
        List<String> cleanedDirectories = new ArrayList<>();
        int i = 0;
        while(i < path.length()){
            i = skipSlashes(path, i);
            Tuple tuple = getNextDirectory(path, i);
            i = tuple.end;
            if(!tuple.directory.equals("")){
                cleanedDirectories.add(tuple.directory);
            }
        }
        return cleanedDirectories;
    }

    private Tuple getNextDirectory(String path, int start){
        int i = start;
        boolean slashFound = false;
        StringBuilder sb = new StringBuilder();
        while(!slashFound && i < path.length()){
            if(path.charAt(i) == '/'){
                slashFound = true;
            }else{
                sb.append(path.charAt(i));
                i++;
            }
        }
        return new Tuple(sb.toString(), i);
    }

    private int skipSlashes(String path, int start){
        int i = start + 1;
        while(i < path.length()){
            if(path.charAt(i) != '/'){
                return i;
            }else{
                i++;
            }
        }
        return path.length();
    }

    public static void main(String[] args){
        SimplifyPath simplifyPath = new SimplifyPath();
        System.out.println(simplifyPath.simplifyPath("/qweeqw////zxczxc/asdads/"));
        System.out.println(simplifyPath.simplifyPath("/qweeqw////zxczxc/../asdads/"));
        System.out.println(simplifyPath.simplifyPath("/../"));
    }
}
