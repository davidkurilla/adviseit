package edu.greenriver.sdev.adviseit.model.structures;

import edu.greenriver.sdev.adviseit.model.dto.CourseDTO;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CourseGraph {

    private final Map<CourseDTO, List<CourseDTO>> graph;

    public CourseGraph(List<CourseDTO> courseDTOList) {
        graph = new HashMap<>();
        for (CourseDTO courseDTO : courseDTOList) {
            addVertex(courseDTO);
        }
    }

    public void addVertex(CourseDTO vertex) {
        graph.put(vertex, new LinkedList<>());
    }

    public void addEdge(CourseDTO source, CourseDTO destination) {
        if (!graph.containsKey(source)) {
            addVertex(source);
        }
        if (!graph.containsKey(destination)) {
            addVertex(destination);
        }
        graph.get(source).add(destination);
    }

    public void removeVertex(CourseDTO vertex) {
        graph.remove(vertex);
    }

    public void removeEdge(CourseDTO source, CourseDTO destination) {
        if (!graph.containsKey(source)) {
            return;
        }
        if (!graph.containsKey(destination)) {
            return;
        }
        graph.get(source).remove(destination);
    }

    public boolean hasCycle() {

        Set<CourseDTO> visited = new HashSet<>();
        Set<CourseDTO> recursionStack = new HashSet<>();

        for (CourseDTO vertex : graph.keySet()) {
            if (!visited.contains(vertex) && hasCycleUtil(vertex, visited, recursionStack)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasCycleUtil(CourseDTO vertex, Set<CourseDTO> visited, Set<CourseDTO> recursionStack) {

        visited.add(vertex);
        recursionStack.add(vertex);

        for (CourseDTO neighbor : graph.getOrDefault(vertex, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                if (hasCycleUtil(neighbor, visited, recursionStack)) {
                    return true;
                }
            } else if (recursionStack.contains(neighbor)) {
                return true;
            }
        }

        recursionStack.remove(vertex);

        return false;
    }

    public List<CourseDTO> sortCourses() {
        Stack<CourseDTO> stack = new Stack<>();
        Set<CourseDTO> visited = new HashSet<>();

        for (CourseDTO vertex : graph.keySet()) {
            if (!visited.contains(vertex)) {
                sortUtil(vertex, visited, stack);
            }
        }

        List<CourseDTO> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    private void sortUtil(CourseDTO vertex, Set<CourseDTO> visited, Stack<CourseDTO> stack) {
        visited.add(vertex);

        for (CourseDTO neighbor : graph.getOrDefault(vertex, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                sortUtil(neighbor, visited, stack);
            }
        }

        stack.push(vertex);
    }
}
