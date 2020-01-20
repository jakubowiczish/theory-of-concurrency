package dmeshpararell.mesh;

import production.PDrawer;

public class GraphDrawer implements PDrawer<Vertex> {

    @Override
    public void draw(Vertex vertex) {

        while (vertex.getLeft() != null) {
            vertex = vertex.getLeft();
        }

        while (vertex.getRight() != null) {
            System.out.print(vertex.getLabel() + "--");
            vertex = vertex.getRight();
        }

        System.out.println(vertex.getLabel());
    }
}
