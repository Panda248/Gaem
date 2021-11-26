package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import world.GameMap;
import world.TileType;
import world.TiledGameMap;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	OrthographicCamera cam;
	GameMap gameMap;

	@Override
	public void create () {
		batch = new SpriteBatch();

		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		gameMap = new TiledGameMap();

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gameMap.update(Gdx.graphics.getDeltaTime());
		gameMap.render(cam, batch);

		if (Gdx.input.isTouched()){
			cam.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
			cam.update();
		}

		if (Gdx.input.justTouched()){
			Vector3 pos = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			TileType type = gameMap.tileByLocation(0, pos.x, pos.y);

			if (type != null){
				System.out.println("Clicked on: " + type.getId() + " , " + type.getName() + " , " + type.isCollidable() + " , " + type.getDamage());
			}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		gameMap.dispose();
	}
}
