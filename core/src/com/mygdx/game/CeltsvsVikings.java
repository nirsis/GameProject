package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Scenes.Gameplay;
import Scenes.MainMenu;
import sun.applet.Main;


public class CeltsvsVikings extends Game {



	private Screen gameplay = new Gameplay();
	//private Screen mainMenu = new MainMenu(this);
	private SpriteBatch spriteBatch;
	private BitmapFont bitmapFont;



	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		bitmapFont = new BitmapFont();
		setScreen(gameplay);
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
	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

	public BitmapFont getBitmapFont() {
		return bitmapFont;
	}
}

