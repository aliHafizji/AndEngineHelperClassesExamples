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
import org.andengine.entity.scene.Scene;
import org.andengine.helperclasses.Slider;
import org.andengine.helperclasses.Slider.OnSliderValueChangeListener;
import org.andengine.opengl.texture.Texture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

import android.util.Log;

public class SliderExample extends AndEngineHelperClassesExamplesBaseClass implements OnSliderValueChangeListener {
	
	private Texture mSliderTexture, mThumbTexture;
	private ITextureRegion mSliderTextureRegion, mThumbTextureRegion;
	
	@Override
	protected void onCreateResources() {
		try {
			this.mSliderTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/slider.png");
				}
			});

			this.mSliderTexture.load();
			
			this.mThumbTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/sliderThumb.png");
				} 
			});
			
			this.mThumbTexture.load();
			
			this.mSliderTextureRegion = TextureRegionFactory.extractFromTexture(this.mSliderTexture);
			this.mThumbTextureRegion = TextureRegionFactory.extractFromTexture(this.mThumbTexture);
		} catch (IOException e) {
			Debug.e(e);
		}
	}

	@Override
	protected Scene onCreateScene() {
		final Scene scene = new Scene();
		Slider slider = new Slider(this.mSliderTextureRegion, this.mThumbTextureRegion, getVertexBufferObjectManager());
		slider.setPosition(CAMERA_WIDTH/2 - slider.getWidth()/2, CAMERA_HEIGHT/2);
		slider.setOnSliderValueChangeListener(this);
		scene.attachChild(slider);
		scene.registerTouchArea(slider.getmThumb());
		scene.setTouchAreaBindingOnActionDownEnabled(true);
		return scene;
	}

	@Override
	public void onSliderValueChanged(float value) {
		Log.d("CHECK!!!!!", " Value is changed to " + value);
	}

}