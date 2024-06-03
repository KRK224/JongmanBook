package test;

import java.util.Map;

public class ResolveGerenicFQN {

    public static void main(String[] args) {
        Map<String, String> typeFQNMap = Map.of("List", "java.util.List", "Scanner", "java.util.Scanner",
            "ArrayList", "java.util.ArrayList", "Map", "java.util.Map", "Set", "java.util.Set", "String", "java.lang.String");

        ResolveGerenicFQN resolveGerenicFQN = new ResolveGerenicFQN();

        String typeName = "Map<String, List<String, String>>, Set, Scanner";

        String tobeResolved = typeName.substring(typeName.indexOf("<"));

        System.out.println(resolveGerenicFQN.resolveGenericFQN(tobeResolved, typeFQNMap));


    }

    private String resolveGenericFQN(String genericType, Map<String, String> typeFQNMap) {
        StringBuilder resolvedFQN = new StringBuilder();
        int start = 0;
        int end = 0;

        while (end < genericType.length()) {
            char c = genericType.charAt(end);
            if (genericType.charAt(end) == '<' || genericType.charAt(end) == '>'
                || genericType.charAt(end) == ',' || genericType.charAt(end) == ' ') {
                if (start != end) {
                    resolvedFQN.append(typeFQNMap.get(genericType.substring(start, end)));
                }
                resolvedFQN.append(c);
                start = ++end;
            } else {
                end++;
            }
        }
        if (start != end) {
            resolvedFQN.append(typeFQNMap.get(genericType.substring(start, end)));
        }
        return resolvedFQN.toString();
    }

}
