package oopokemon.misc;

import oopokemon.map.Map;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class Renderer {
    private List<Renderable> toRender;

    /**
     * @param map is what to render
     */
    public Renderer(Map map) {
        toRender = new ArrayList<>(map.getMap());
        map.getMap().stream().filter(c -> c.occupier != null).forEach(c -> toRender.add(c.occupier));
    }

    /**
     * @param map is what to render
     * @param mapPlaceHolder is where to render
     */
    public static void renderAll(Map map, Pane mapPlaceHolder) {
        map.getMap()
                .forEach(cell -> {
                    mapPlaceHolder.getChildren().add(cell.getToRender());
                });
        map.getMap()
                .stream()
                .filter(cell -> cell.occupier != null)
                .forEach(cell -> mapPlaceHolder.getChildren().add(cell.occupier.getToRender()));
    }

    /**
     * @param map is what to render
     * @param mapPlaceHolder is where to unRender
     */
    public static void unRenderAll(Map map, Pane mapPlaceHolder){
        map.getMap()
                .forEach(cell -> {
                    mapPlaceHolder.getChildren().remove(cell.getToRender());
                    if (cell.occupier != null){
                        Node renderable = cell.occupier.getToRender();
                        mapPlaceHolder.getChildren().remove(renderable);
                    }
                });
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
