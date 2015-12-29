using UnityEngine;
using System.Collections;

public class title : MonoBehaviour {

	public SpriteRenderer sr;
	public SpriteRenderer srC;
	// Use this for initialization
	void Awake () {
		sr.color = new Color (sr.color.r,sr.color.g,sr.color.b,0f);
		srC.color = new Color (sr.color.r,sr.color.g,sr.color.b,0f);
	}
	
	// Update is called once per frame
	void Update () {
		sr.color = new Color (sr.color.r,sr.color.g,sr.color.b,sr.color.a+0.5f*Time.deltaTime);
		srC.color = new Color (sr.color.r,sr.color.g,sr.color.b,srC.color.a+0.5f*Time.deltaTime);
	}
}
