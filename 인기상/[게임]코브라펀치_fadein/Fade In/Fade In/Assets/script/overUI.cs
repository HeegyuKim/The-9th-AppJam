using UnityEngine;
using System.Collections;
using UnityEngine.UI;
public class overUI : MonoBehaviour {

	public Transform top;
	public Transform bottom;
	public Text best;
	public Text now;
	bool moveEnd = false;
	
	// Update is called once per frame
	void Update () {
		if (!GM.isOver)
			return;
		Vector2 oriVector = bottom.position;

		top.localPosition = Vector2.Lerp (top.localPosition, new Vector2 (0, 0), Time.deltaTime  );
		bottom.localPosition = Vector2.Lerp (bottom.localPosition, new Vector2 (0, 0), Time.deltaTime );

		Vector2 moveVector = new Vector2(bottom.localPosition.x,bottom.localPosition.y) - oriVector;
		if (moveVector.y < 0.2f && !moveEnd) {
			if(PlayerPrefs.GetInt("best",0) < GM.score)
				PlayerPrefs.SetInt("best",GM.score);
			moveEnd = true;
			best.text = PlayerPrefs.GetInt("best",0).ToString();
			now.text = GM.score.ToString();
		}
		if (Input.GetMouseButtonDown(0) && GM.isOver) {
			if(!moveEnd){
				top.localPosition = Vector2.zero;
				bottom.localPosition = Vector2.zero;
				if(PlayerPrefs.GetInt("best",0) < GM.score)
					PlayerPrefs.SetInt("best",GM.score);
				moveEnd = true;
				best.text = PlayerPrefs.GetInt("best",0).ToString();
				now.text = GM.score.ToString();
			}else{
				Application.LoadLevel(0);
			}

		}
	}
}
