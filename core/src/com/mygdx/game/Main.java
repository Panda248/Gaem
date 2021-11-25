package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import world.GameMap;
import world.TiledGameMap;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	OrthographicCamera cam;
	GameMap gameMap;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		gameMap = new TiledGameMap();

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		gameMap.render(cam);

		if (Gdx.input.isTouched()){
			cam.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
			cam.update();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
