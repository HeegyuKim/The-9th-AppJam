using UnityEngine;
using System.Collections;
using UnityEngine.UI;
public class playerCol : MonoBehaviour {

	public int starScore;
	public Text starT;
	 playerchange pc;
	public Canvas c;

	// Use this for initialization
	void Awake () {
		pc = GetComponent<playerchange> ();
	}
	
	// Update is called once per frame
	void Update () {

		starT.text = "" + starScore + "/" + (30 +GM.stageLevel * 20);
		if (starScore >= pc.playerLevel * 15) {
			pc.upLevel();
		}
		else if (starScore < (pc.playerLevel-1) * 15) {
			pc.downLevel();
		}
	}
	public void chitScore(){
		starScore++;
	}

	void OnTriggerEnter2D(Collider2D col){
		if (col.tag == "star") {//star

			if(starScore < 30+20*GM.stageLevel){
				GM.score+=10;
				starScore++;
			}
			mobSpawn.starCount --;
			Destroy(col.gameObject);

		}
		else if(col.tag == "skill"){

		}else if(col.tag == "brain"){
			
		}else {//bullet,enemy ...
			//c.enabled = false;
			if(GM.mu)
				return;
			GM.stageLevel = 0;
			GM.isStop = false;
			GM.score = 0;
			GM.isOver = true;
		}
	}
}
