using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class pause : MonoBehaviour {
	public Image sr;
	bool isPause=false;

	void Awake(){
		sr.color = new Color (sr.color.r,sr.color.g,sr.color.b,0f);
	}

	// Update is called once per frame
	void Update () {
	
	}

	public void dopause(){
		if (!isPause) {
			Time.timeScale = 0;
			isPause = true;
			sr.color = new Color (sr.color.r,sr.color.g,sr.color.b,1f);
		} else {

		}
	}
	public void resume(){
		if (isPause) {
			Time.timeScale = 1;
			sr.color = new Color (sr.color.r, sr.color.g, sr.color.b, 0f);
			isPause = false;
		}
	}
}
