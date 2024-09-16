import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

class AStarPathingStrategy implements PathingStrategy {


    private static final Comparator<Node> COST_COMPARATOR =
            Comparator.comparingInt(node -> node.cost + node.estimatedCostToGoal);

    private static class Node {
        private Point point;
        private int cost;
        private int estimatedCostToGoal;

        Node(Point point, int cost, int estimatedCostToGoal) {
            this.point = point;
            this.cost = cost;
            this.estimatedCostToGoal = estimatedCostToGoal;
        }

        public Point getPoint() {
            return this.point;
        }
    }

    @Override
    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors) {

        PriorityQueue<Node> openSet = new PriorityQueue<>(COST_COMPARATOR);
        Set<Point> closedSet = new HashSet<>();
        Map<Point, Integer> costMap = new HashMap<>();
        Map<Point, Point> cameFrom = new HashMap<>();

        openSet.add(new Node(start, 0, heuristicCost(start, end)));
        costMap.put(start, 0);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (withinReach.test(current.getPoint(), end)) {
                return reconstructPath(cameFrom, current.getPoint(), start);
            }

            closedSet.add(current.getPoint());

            potentialNeighbors.apply(current.point)
                    .filter(canPassThrough)
                    .filter(pt -> !closedSet.contains(pt))
                    .forEach(neighbor -> {
                        int tentativeCost = costMap.get(current.getPoint()) + 1;

                        if (!costMap.containsKey(neighbor) || tentativeCost < costMap.get(neighbor)) {
                            costMap.put(neighbor, tentativeCost);
                            int estimatedCostToGoal = tentativeCost + heuristicCost(neighbor, end);
                            openSet.add(new Node(neighbor, tentativeCost, estimatedCostToGoal));
                            cameFrom.put(neighbor, current.getPoint());
                        }
                    });
        }
//        System.out.println("No Path Found");
        List<Point> path = new ArrayList<>();
        path.add(start);
        return path;
    }

    private List<Point> reconstructPath(Map<Point, Point> cameFrom, Point current, Point start) {
        List<Point> path = new ArrayList<>();
        path.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);

            if (current.equals(start)) {
                path.add(current);
                break;
            }
            path.add(current);
        }
        path.removeLast();

        return path.reversed();
    }

    private int heuristicCost(Point current, Point goal) {
        return Math.abs(goal.getX() - current.getX()) + Math.abs(goal.getY() - current.getY());
    }
}
