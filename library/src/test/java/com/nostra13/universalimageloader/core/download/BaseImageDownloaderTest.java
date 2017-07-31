package com.nostra13.universalimageloader.core.download;

import android.app.Activity;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;

import java.io.InputStream;

@RunWith(RobolectricTestRunner.class)
public class BaseImageDownloaderTest {

	@Test
	public void testSchemeHttp() throws Exception {
		String uri = "http://image.com/1.png";
		Scheme result = Scheme.ofUri(uri);
		Scheme expected = Scheme.HTTP;
		Assertions.assertThat(result).isEqualTo(expected);
	}

	@Test
	public void testSchemeHttps() throws Exception {
		String uri = "https://image.com/1.png";
		Scheme result = Scheme.ofUri(uri);
		Scheme expected = Scheme.HTTPS;
		Assertions.assertThat(result).isEqualTo(expected);
	}

	@Test
	public void testSchemeContent() throws Exception {
		String uri = "content://path/to/content";
		Scheme result = Scheme.ofUri(uri);
		Scheme expected = Scheme.CONTENT;
		Assertions.assertThat(result).isEqualTo(expected);
	}

	@Test
	public void testSchemeAssets() throws Exception {
		String uri = "assets://folder/1.png";
		Scheme result = Scheme.ofUri(uri);
		Scheme expected = Scheme.ASSETS;
		Assertions.assertThat(result).isEqualTo(expected);
	}

	@Test
	public void testSchemeDrawables() throws Exception {
		String uri = "drawable://123456890";
		Scheme result = Scheme.ofUri(uri);
		Scheme expected = Scheme.DRAWABLE;
		Assertions.assertThat(result).isEqualTo(expected);
	}

	@Test
	public void testSchemeFile() throws Exception {
		String uri = "file://path/on/the/device/1.png";
		Scheme result = Scheme.ofUri(uri);
		Scheme expected = Scheme.FILE;
		Assertions.assertThat(result).isEqualTo(expected);
	}

	@Test
	public void testSchemeUnknown() throws Exception {
		String uri = "other://image.com/1.png";
		Scheme result = Scheme.ofUri(uri);
		Scheme expected = Scheme.UNKNOWN;
		Assertions.assertThat(result).isEqualTo(expected);
	}

	@Test
	public void testUserInfoUrl1() throws Exception {
		String uri = "http://test_account:test12345@pwdpic.heliohost.org/password_picture/example.jpg";
		InputStream picture = new BaseImageDownloader(new Activity()).getStream(uri, null);
		Assertions.assertThat(picture).isNotNull();
	}

	@Test
	public void testUserInfoUrl2() throws Exception {
		String uri = "http://test_account1:test67890@pwdpic.heliohost.org/password_picture/%21%40%23%24%25%5E%26%2A%28%29.png";
		InputStream picture = new BaseImageDownloader(new Activity()).getStream(uri, null);
		Assertions.assertThat(picture).isNotNull();
	}

	@Test
	public void testUserInfoUrl3() throws Exception {
		String uri = "http://play%23er:mikeJ%21%24@pwdpic.heliohost.org/password_picture/example1.png";
		InputStream picture = new BaseImageDownloader(new Activity()).getStream(uri, null);
		Assertions.assertThat(picture).isNotNull();
	}

	@Test
	public void testUserInfoUrl4() throws Exception {
		String uri = "http://%21%40%23%24%25%5E%26%2A%28%29test:%21%40%23%24%25%5E%26%2A%28%29@pwdpic.heliohost.org/password_picture/example2.bmp";
		InputStream picture = new BaseImageDownloader(new Activity()).getStream(uri, null);
		Assertions.assertThat(picture).isNotNull();
	}

	@Test
	public void testUserInfoUrl5() throws Exception {
		String uri = "http://this_%2Ais%40%24%7E_a%2A%25%40_test%24123:this_%40%5E%21%23_is%29%5E%26%28_a%40%23%25%5E%26%7E423_password%21@pwdpic.heliohost.org/password_picture/%21%40%23%24%25%5E%26%2A%28%29.png";
		InputStream picture = new BaseImageDownloader(new Activity()).getStream(uri, null);
		Assertions.assertThat(picture).isNotNull();
	}
}