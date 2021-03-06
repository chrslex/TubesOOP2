package oopokemon.misc;

import javafx.application.Platform;
import oopokemon.map.Map;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class Renderer {
    private final List<Renderable> toRender;

    /**
     * @param map is what to render
     */
    public Renderer(Map map) {
        toRender = new ArrayList<>(map.getCells());
        map.getCells().stream().filter(c -> c.occupier != null).forEach(c -> toRender.add(c.occupier));
    }

    /**
     * @param map is what to render
     * @param mapPlaceHolder is where to render
     */
    public static void renderAll(Map map, Pane mapPlaceHolder) {
        map.getCells()
                .forEach(cell -> {
                    mapPlaceHolder.getChildren().add(cell.getToRender());
                });
        map.getCells()
                .stream()
                .filter(cell -> cell.occupier != null)
                .map(cell -> cell.occupier.getToRender())
                .forEach(node -> mapPlaceHolder.getChildren().add(node));
    }

    /**
     * @param map is what to render
     * @param mapPlaceHolder is where to unRender
     */
    public static void unRenderAll(Map map, Pane mapPlaceHolder){
        map.getCells()
                .forEach(cell -> {
                    mapPlaceHolder.getChildren().remove(cell.getToRender());
                    if (cell.occupier != null){
                        Node renderable = cell.occupier.getToRender();
                        mapPlaceHolder.getChildren().remove(renderable);
                    }
                });
    }

    public static void renderNode(Node toRender, Pane mapPlaceHolder) {
        Platform.runLater(() -> mapPlaceHolder.getChildren().add(toRender));
    }

    /**
     * @param mapPlaceHolder is where to render
     */
    public void render(Pane mapPlaceHolder){
        toRender.forEach(renderable -> mapPlaceHolder.getChildren().add(renderable.getToRender()));
    }

    /**
     * @param mapPlaceHolder is where to unRender
     */
    public void unRender(Pane mapPlaceHolder){
        toRender.forEach(renderable -> mapPlaceHolder.getChildren().remove(renderable.getToRender()));
    }

}
