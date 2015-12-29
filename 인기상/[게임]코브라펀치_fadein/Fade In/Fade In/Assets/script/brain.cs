using UnityEngine;
using System.Collections;

public class brain : MonoBehaviour {
	public int req=0;
	public bool success = false;
	bool isPlayer = false;
	public SpriteRenderer sr;
	public SpriteRenderer srC;
	float timerTime = 0.0f;
	public SpriteRenderer effect;
	bool waking = false;

	// Use this for initialization
	void Awake () {

		StartCoroutine("wakeUp");
		sr.color = new Color (sr.color.r,sr.color.g,sr.color.b,0f);
		srC.color = new Color (srC.color.r,srC.color.g,srC.color.b,1f);
	}
	
	// Update is called once per frame
	void Update () {
		if (success) {
			sr.color = new Color (sr.color.r,sr.color.g,sr.color.b,1f);
			srC.color = new Color (srC.color.r,srC.color.g,srC.color.b,0f);
			return;
		}
		if (isPlayer && !waking) {
			timerTime +=Time.deltaTime;
			if(timerTime >=3f){
				if(GameObject.Find("player").GetComponent<playerCol>().starScore >= req)
				{
					
					Debug.Log("gd");
				GameObject.Find("player").GetComponent<tilt>().waking = true;
				waking = true;
				}
			}
		}
		if (waking) {
			GameObject.Find("player").transform.Rotate(0,0,Time.deltaTime * 540);
		}
	}
	IEnumerator wakeUp(){
		while(true){
			if(waking){
			if(GameObject.Find("player").GetComponent<playerCol>().starScore<=0){
					GM.stageLevel++;
					waking = false;
					isPlayer = false;
					GameObject.Find("player").GetComponent<tilt>().waking = false;
					success = true;
					GM.score+=2000;
					effect.color = new Color (effect.color.r,effect.color.g,effect.color.b,1f);
					Debug.Log("success");
				break;
			}else{
				GameObject.Find("player").GetComponent<playerCol>().starScore--;
			};
			
			}
			yield return new WaitForSeconds(0.1f);
		}
	}

	void OnTriggerEnter2D(Collider2D col){
		if (col.name == "player") {
			if(success)
				return;
			isPlayer = true;
			timerTime = 0f;

		} else {//bullet,enemy ...
			if(col.name == "gameEnemy01")
				mobSpawn.enemyCount1--;
			if(col.name == "gameEnemy02")
				mobSpawn.enemyCount2--;
			if(col.name == "gameEnemy03")
				mobSpawn.enemyCount3--;
			Destroy(col.gameObject);
			//Application.Quit();
		}
	}

	void OnTriggerExit2D(Collider2D col){
		if (col.name == "player") {
			if(success)
				return;
			isPlayer = false;
			timerTime = 0f;
			
		}
	}

}
