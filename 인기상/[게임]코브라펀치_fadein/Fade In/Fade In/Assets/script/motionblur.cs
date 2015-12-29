using UnityEngine;
using System.Collections;

public class motionblur : MonoBehaviour {

	public Vector2 mbscale = new Vector2(80,80);
	float mbDestroySpeed = 0.2f;


	SpriteRenderer sr;
	// Use this for initialization
	void Awake () {
		sr = GetComponent<SpriteRenderer> ();

		mbDestroySpeed = Random.Range (mbDestroySpeed,mbDestroySpeed+0.4f);

	}
	
	// Update is called once per frame
	void Update () {
		if (GM.isOver)
			return;
		sr.color = new Color(sr.color.r,sr.color.g,sr.color.b,sr.color.a-(1f/mbDestroySpeed)*Time.deltaTime);
		if (sr.color.a < 0) {
			Destroy(gameObject);
		}
	}


	public void doInit(GameObject p,float mds){
		GameObject Player = p;
		sr.sprite= Player.GetComponent<SpriteRenderer> ().sprite;
		transform.localScale = mbscale;
		transform.position = Player.transform.position;
		transform.rotation = Player.transform.rotation;

		mbDestroySpeed = Random.Range (mds,mds+0.4f);

		sr.color = new Color(sr.color.r,sr.color.g,sr.color.b,1f);
	}
}
