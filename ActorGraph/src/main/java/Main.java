import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.graph.SimpleGraph;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //System.out.print("Enter first actor");
        //String firstActor = br.readLine();
        //System.out.print("Enter second actor:");
        //String secondActor = br.readLine();
        String firstActorName = "Kit Harington";
        String secondActorName = "Bruce Willis";
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
        HashMap<Actor, Movie> actorMovieHashMap;
        List<Actor> globalActors = new ArrayList<>(  );
        globalActors.add( firstActor );

        for ( int i = 0; i < globalActors.size(); i++ ){
            Actor actor = globalActors.get( i );
                actorMovieHashMap = RequestsHelper.FetchActorsFromAllMovies(actor);
                graph = FillGraph( graph, actorMovieHashMap, actor);
                if(IsThereActor( actorMovieHashMap, secondActor ))
                    break;
                List<Actor> actors = new ArrayList<>(actorMovieHashMap.keySet());
                globalActors.addAll( actors );
                globalActors.remove( 0 );
        }

        Set<Actor> vertices = graph.vertexSet();
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

    private static boolean IsThereActor(HashMap<Actor, Movie> actorMovieHashMap, Actor actorToSearch){
        return actorMovieHashMap.containsKey( actorToSearch );
    }

    private static Graph<Actor, Movie> FillGraph(Graph<Actor, Movie> graph, HashMap<Actor, Movie> actorMovieHashMap, Actor actualActor){
        actorMovieHashMap.remove( actualActor );
        graph.addVertex( actualActor );
        for(Map.Entry<Actor, Movie> entry : actorMovieHashMap.entrySet()) {
            graph.addVertex( entry.getKey() );
            graph.addEdge( actualActor, entry.getKey(), entry.getValue() );
        }
        return graph;
    }


}
