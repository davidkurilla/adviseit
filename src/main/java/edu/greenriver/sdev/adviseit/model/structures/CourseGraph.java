package edu.greenriver.sdev.adviseit.model.structures;

import edu.greenriver.sdev.adviseit.model.entities.Course;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CourseGraph {

    private final Map<Course, List<Course>> graph;

    public CourseGraph() {
        this.graph = new HashMap<>();
    }

    public void addVertex(Course vertex) {
        graph.put(vertex, new LinkedList<>());
    }

    public void addEdge(Course source, Course destination) {
        if (!graph.containsKey(source)) {
            addVertex(source);
        }
        if (!graph.containsKey(destination)) {
            addVertex(destination);
        }
        graph.get(source).add(destination);
    }

    public void removeVertex(Course vertex) {
        graph.remove(vertex);
    }

    public void removeEdge(Course source, Course destination) {
        if (!graph.containsKey(source)) {
            return;
        }
        if (!graph.containsKey(destination)) {
            return;
        }
        graph.get(source).remove(destination);
    }

    public boolean hasCycle() {

        Set<Course> visited = new HashSet<>();
        Set<Course> recursionStack = new HashSet<>();

        for (Course vertex : graph.keySet()) {
            if (!visited.contains(vertex) && hasCycleUtil(vertex, visited, recursionStack)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasCycleUtil(Course vertex, Set<Course> visited, Set<Course> recursionStack) {

        visited.add(vertex);
        recursionStack.add(vertex);

        for (Course neighbor : graph.getOrDefault(vertex, Collections.emptyList())) {
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

    public List<Course> sortCourses() {
        Stack<Course> stack = new Stack<>();
        Set<Course> visited = new HashSet<>();

        for (Course vertex : graph.keySet()) {
            if (!visited.contains(vertex)) {
                sortUtil(vertex, visited, stack);
            }
        }

        List<Course> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    private void sortUtil(Course vertex, Set<Course> visited, Stack<Course> stack) {
        visited.add(vertex);

        for (Course neighbor : graph.getOrDefault(vertex, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                sortUtil(neighbor, visited, stack);
            }
        }

        stack.push(vertex);
    }
}
