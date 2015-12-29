using UnityEngine;
using System.Collections;

public class skill : MonoBehaviour {
	public Sprite[] sprites;
	int level;
	// Use this for initialization
	void Awake () {
		level = GameObject.Find("player").GetComponent<playerchange>().playerLevel;
		transform.position = GameObject.Find ("player").transform.position;

		if (level == 1) {
			GetComponent<SpriteRenderer>().sprite = sprites[0];
		}
		if (level == 2) {
			GetComponent<SpriteRenderer>().sprite = sprites[1];
		}
		if (level ==3) {
			GetComponent<SpriteRenderer>().sprite = sprites[2];
		}
		if (level == 4) {
			GetComponent<SpriteRenderer>().sprite = sprites[2];
		}
		if (level == 5) {
			GetComponent<SpriteRenderer>().sprite = sprites[3];
		}
		if (level == 6) {
			GetComponent<SpriteRenderer>().sprite = sprites[4];
		}

		Destroy (gameObject, 1f);
	}
	
	// Update is called once per frame
	void Update () {
		if (GM.isOver)
			return;
		transform.Rotate (0, 0, 360 * Time.deltaTime * -1);
		if (level == 1) {
			transform.localScale = Vector2.Lerp (transform.localScale, new Vector2 (30, 30),Time.deltaTime*30f);
		}
		if (level == 2) {
			transform.localScale = Vector2.Lerp (transform.localScale, new Vector2 (40, 40),Time.deltaTime*30f);
		}
		if (level ==3) {
			transform.localScale = Vector2.Lerp (transform.localScale, new Vector2 (50, 50),Time.deltaTime*30f);
		}
		if (level == 4) {
			transform.localScale = Vector2.Lerp (transform.localScale, new Vector2 (60, 60),Time.deltaTime*30f);
		}
		if (level == 5) {
			transform.localScale = Vector2.Lerp (transform.localScale, new Vector2 (80, 80),Time.deltaTime*30f);
		}
		if (level == 6) {
			transform.localScale = Vector2.Lerp (transform.localScale, new Vector2 (100, 100),Time.deltaTime*30f);
		}

	}

	void OnTriggerEnter2D(Collider2D col){
		if (col.name == "player") {
		} else {
			if(col.name == "gameEnemy01")
				mobSpawn.enemyCount1--;
			if(col.name == "gameEnemy02")
				mobSpawn.enemyCount2--;
			if(col.name == "gameEnemy03")
				mobSpawn.enemyCount3--;
			GM.score+=10;
			Destroy(col.gameObject);
		}
	}
}
