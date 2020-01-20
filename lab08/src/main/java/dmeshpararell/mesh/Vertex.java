package dmeshpararell.mesh;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vertex {

    private Vertex left;
    private Vertex right;
    private String label;
}
