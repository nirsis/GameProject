package com.mygdx.game;

import com.badlogic.gdx.Game;
import Scenes.Gameplay;


public class CeltsvsVikings extends Game {


	@Override
	public void create() {
		setScreen(new Gameplay());
	}

	@Override
	public void dispose() {
		super.dispose();
	}
	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();

	}

	@Override
	public void resume() {
		super.resume();
	}
}

