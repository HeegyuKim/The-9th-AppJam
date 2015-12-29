using UnityEngine;
using System.Collections;

public class playerchange : MonoBehaviour {

	public int playerLevel = 1;

	public Vector2 colOffset1;
	public Vector2 colSize1;

	public Vector2 colOffset2;
	public Vector2 colSize2;

	public Vector2 colOffset3;
	public Vector2 colSize3;

	public Vector2 colOffset4;
	public Vector2 colSize4;

	public Vector2 colOffset5;
	public Vector2 colSize5;

	public Vector2 colOffset6;
	public Vector2 colSize6;

	SpriteRenderer sr;
	BoxCollider2D bc;

	public SpriteRenderer effect;

	public Sprite[] p_s = new Sprite[6];

	// Use this for initialization
	void Awake () {
		sr = GetComponent<SpriteRenderer> ();
		bc = GetComponent<BoxCollider2D> ();
		sr.sprite = p_s[0];
		bc.offset = colOffset1;
		bc.size = colSize1;
		effect.color = new Color (effect.color.r,effect.color.g,effect.color.b,0f);
	}
	
	// Update is called once per frame
	void Update () {
		effect.color = new Color (effect.color.r,effect.color.g,effect.color.b,0f);
	}

	public void upLevel(){
		if (playerLevel != 6) {
			playerLevel++;
			
			changeLevel ();
		}
	}

	public void downLevel(){
		if (playerLevel != 1) {
			playerLevel--;
			
			changeLevel ();
		}
	}

	void changeLevel(){
		Debug.Log ("color");
		effect.color = new Color (effect.color.r,effect.color.g,effect.color.b,1f);
		sr.sprite = p_s[playerLevel-1];

		if (playerLevel == 1) {
			bc.offset = colOffset1;
			bc.size = colSize1;
		}
		if (playerLevel == 2) {
			bc.offset = colOffset2;
			bc.size = colSize2;
		}
		if (playerLevel == 3) {
			bc.offset = colOffset3;
			bc.size = colSize3;
		}
		if (playerLevel == 4) {
			bc.offset = colOffset4;
			bc.size = colSize4;
		}
		if (playerLevel == 5) {
			bc.offset = colOffset5;
			bc.size = colSize5;
		}
		if (playerLevel == 6) {
			bc.offset = colOffset6;
			bc.size = colSize6;
		}



		//change effect go
	}
}
