/* Copyright 2012 Olie

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. */

package org.andengine.helperclasses.examples;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.Texture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;
import org.andengine.helperclasses.LevelSelector;
import org.andengine.helperclasses.LevelSelector.Padding;
import org.andengine.input.touch.TouchEvent;

public class LevelSelectorExample extends SimpleBaseGameActivity implements IOnSceneTouchListener {

	private static int CAMERA_WIDTH = 360;
	private static int CAMERA_HEIGHT = 240;
	private Texture mItem;
	private ITextureRegion mItemTextureRegion;
	
	private ArrayList<Sprite> mLevelItems;
	private LevelSelector mLevelSelector;
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
	}

	@Override
	protected void onCreateResources() {
		try {
			this.mItem = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/LevelButton.png");
				}
			});

			this.mItem.load();
			this.mItemTextureRegion = TextureRegionFactory.extractFromTexture(this.mItem);
		} catch (IOException e) {
			Debug.e(e);
		}
	}

	@Override
	protected Scene onCreateScene() {
		final Scene scene = new Scene();
		
		mLevelItems = new ArrayList<Sprite>();
		
		for(int i = 0; i < 10; i++) {
			Sprite menuItem = new Sprite(0, 0, this.mItemTextureRegion, this.getVertexBufferObjectManager());
			this.mLevelItems.add(menuItem);
		}
		mLevelSelector = new LevelSelector(this.mLevelItems, 2, 2, new Padding(80, 100), getEngine().getCamera(), true);
		scene.attachChild(mLevelSelector);

		scene.setOnSceneTouchListener(this);
		scene.setTouchAreaBindingOnActionDownEnabled(true);
		scene.setOnSceneTouchListenerBindingOnActionDownEnabled(true);
		return scene;
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		mLevelSelector.onTouchEvent(pSceneTouchEvent);
		return true;
	}
}
