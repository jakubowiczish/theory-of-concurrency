package productions;

import dmeshpararell.mesh.Vertex;
import production.AbstractProduction;
import production.PDrawer;

public class P3 extends AbstractProduction<Vertex> {

    public P3(Vertex vertex, PDrawer<Vertex> drawer) {
        super(vertex, drawer);
    }

    @Override
    public Vertex apply(Vertex p) {
        System.out.println("p3");
        Vertex t2 = new Vertex(p.getLeft(), p, "T2");
        p.getLeft().setRight(t2);
        p.setLeft(t2);
        return t2;
    }
}
