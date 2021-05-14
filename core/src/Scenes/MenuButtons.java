package Scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.CeltsvsVikings;

public class MenuButtons {

    private CeltsvsVikings game;
    private Stage stage;
    private Viewport viewport;
    private ImageButton playButton;
    private ImageButton highScore;
    private ImageButton options;
    private ImageButton exit;

    public MenuButtons (CeltsvsVikings game) {
        this.game = game;
        viewport = new FitViewport(400f, 600f, new OrthographicCamera());
        //stage = new Stage(viewport, game.
        stage.addActor(playButton);
        stage.addActor(highScore);

        stage.addActor(options);
        stage.addActor(exit
        );
    }
    public Stage getStage() {
        return this.stage;
    }

    protected void createButtons() {
        playButton = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Scenes/Start Game.png"))));
        highScore = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Scenes/Highscore.png"))));
        options = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Scenes/Options.png"))));
        exit = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Scenes/Quit.png"))));

        playButton.setPosition(200f,300f, Align.center);
        highScore.setPosition(150f,250f, Align.center);
        options.setPosition(100f,200f,Align.center);
        exit.setPosition(50f,150f,Align.center);
    }
}
