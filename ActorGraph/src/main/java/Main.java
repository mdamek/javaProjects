import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.graph.SimpleGraph;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        String firstActorName = "Jason Statham";
        String secondActorName = "Rainn Wilson";
        Actor firstActor = null;
        Actor secondActor = null;
        try {
            firstActor = RequestsHelper.FetchActor( firstActorName );
            secondActor = RequestsHelper.FetchActor( secondActorName );
        }
        catch (IOException e){
            System.out.println( e.getMessage() );
        }
        Graph<Actor, Movie> graph = new SimpleGraph<>( Movie.class);
        Queue<Actor> actorQueue = new LinkedList<>();
        actorQueue.add( firstActor );


        while (!actorQueue.isEmpty()){
            Actor actor = actorQueue.poll();
            List<Association> associations = RequestsHelper.FetchActorsFromAllMovies(actor);
            graph = FillGraph( graph, associations, actor);
            if(IsThereActor( associations, secondActor ))
                break;
            List<Actor> actors = associations.stream()
                    .map( element -> new Actor( element.actor.id, element.actor.name ) ).collect( Collectors.toList() );
            actorQueue.addAll( actors );
        }

        BellmanFordShortestPath<Actor, Movie> bfsp = new BellmanFordShortestPath<>(graph);
        GraphPath<Actor, Movie> shortestPath = bfsp.getPath(firstActor, secondActor);
        List<Movie> edges = shortestPath.getEdgeList();
        List<Actor> actors = shortestPath.getVertexList();
        for(int i = 0; i < actors.size(); ++i){
            if(i == actors.size()-1)
                System.out.print(actors.get(i));
            else
                System.out.print(actors.get(i) + " -> " + edges.get(i).toString() + " -> ");
        }
    }

    private static boolean IsThereActor(List<Association> actorsMovies, Actor actorToSearch){
        for ( Association actual : actorsMovies ) {
            if(actual.actor.id.equals( actorToSearch.id ))
                return true;
        }
        return false;
    }

    private static Graph<Actor, Movie> FillGraph(Graph<Actor, Movie> graph, List<Association> actorsMovies, Actor actualActor)
            throws CloneNotSupportedException {
        actorsMovies.removeIf( element -> element.actor.id.equals( actualActor.id ) );
        graph.addVertex( actualActor );
        for ( Association actual : actorsMovies ) {
            graph.addVertex( actual.actor );
            graph.addEdge( actualActor, actual.actor, (Movie) actual.movie.clone() );
        }
        return graph;
    }


}
