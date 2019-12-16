package productions;

import dmeshpararell.mesh.Vertex;
import production.AbstractProduction;
import production.PDrawer;

public class P5 extends AbstractProduction<Vertex> {

    public P5(Vertex vertex, PDrawer<Vertex> drawer) {
        super(vertex, drawer);
    }

    @Override
    public Vertex apply(Vertex p) {
        System.out.println("p5");
        p.setLabel("Iel1");
        return p;
    }
}
