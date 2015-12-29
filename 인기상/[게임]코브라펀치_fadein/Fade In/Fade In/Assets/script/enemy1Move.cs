using UnityEngine;
using System.Collections;

public class enemy1Move : MonoBehaviour {

	bool isShooting;
	Sprite sprite;
	float moveDir;
	float chTime;
	public float range= 500f;

	public float speed;
	public float BBAckSpeed;

	// Use this for initialization
	void Awake () {
		GetComponentInChildren<motionblurMaker> ().doMake = false;
		sprite = GetComponent<SpriteRenderer> ().sprite;
		chTime = Random.Range (1f,5f);
		StartCoroutine("chDir");
	}
	// Update is called once per frame
	void FixedUpdate () {
		if (GM.isOver) {
			speed = 0;
			BBAckSpeed = 0;
			StopCoroutine("chDir");
		}
		if (!isShooting) {
		
			Vector2 ori_p = transform.position;
			transform.Translate (Vector3.up * Time.deltaTime * speed);


			if (transform.position.x <= -2048f + sprite.rect.width) {
				transform.position = ori_p;
				moveDir = Random.Range (180, 360);
				transform.rotation = Quaternion.AngleAxis (moveDir, Vector3.forward);
			
				transform.position = new Vector2 (transform.position.x + 2, transform.position.y);
			
			}
			if (transform.position.y <= -2048f + sprite.rect.height) {
				moveDir = Random.Range (-90, 90);
				transform.rotation = Quaternion.AngleAxis (moveDir, Vector3.forward);
				transform.position = ori_p;
				transform.position = new Vector2 (transform.position.x, transform.position.y + 2);
			}
		
			if (transform.position.x >= 2048f - sprite.rect.width) {
				moveDir = Random.Range (0, 180);
				transform.rotation = Quaternion.AngleAxis (moveDir, Vector3.forward);
				transform.position = ori_p;
				transform.position = new Vector2 (transform.position.x - 2, transform.position.y);
			}
			if (transform.position.y >= 2048f - sprite.rect.height) {
				moveDir = Random.Range (90, 270);
				transform.rotation = Quaternion.AngleAxis (moveDir, Vector3.forward);
				transform.position = ori_p;
				transform.position = new Vector2 (transform.position.x, transform.position.y - 2);
			}
		} else {
			if (Vector2.Distance (GameObject.Find ("player").transform.position, transform.position) > range) {
				isShooting = false;
				speed-=BBAckSpeed;
				GetComponentInChildren<motionblurMaker> ().doMake = false;
			}else{

				Vector2 ori_p = transform.position;
				transform.Translate (Vector3.up * Time.deltaTime * speed);
				
				
				if (transform.position.x <= -2048f + sprite.rect.width) {
					transform.position = ori_p;
					speed-=BBAckSpeed;
					isShooting = false;GetComponentInChildren<motionblurMaker> ().doMake = false;
					transform.position = new Vector2 (transform.position.x + 2, transform.position.y);
					
				}
				if (transform.position.y <= -2048f + sprite.rect.height) {
					transform.position = ori_p;isShooting = false;speed-=BBAckSpeed;GetComponentInChildren<motionblurMaker> ().doMake = false;
					transform.position = new Vector2 (transform.position.x, transform.position.y + 2);
				}
				
				if (transform.position.x >= 2048f - sprite.rect.width) {
					transform.position = ori_p;isShooting = false;speed-=BBAckSpeed;GetComponentInChildren<motionblurMaker> ().doMake = false;
					transform.position = new Vector2 (transform.position.x - 2, transform.position.y);
				}
				if (transform.position.y >= 2048f - sprite.rect.height) {
					transform.position = ori_p;isShooting = false;speed-=BBAckSpeed;GetComponentInChildren<motionblurMaker> ().doMake = false;
					transform.position = new Vector2 (transform.position.x, transform.position.y - 2);
				}

			}
		}
	}
	void Update(){
		if (!isShooting) {
			if (Vector2.Distance (GameObject.Find ("player").transform.position, transform.position) < range) {
				isShooting = true;
				speed += BBAckSpeed;

				Vector3 vectorToTarget = GameObject.Find ("player").transform.position - transform.position;
				float angle = Mathf.Atan2(vectorToTarget.y, vectorToTarget.x) * Mathf.Rad2Deg;
				transform.rotation = new Quaternion(0,0,0,0);
				transform.Rotate (0, 0, angle-90f);
				GetComponentInChildren<motionblurMaker> ().doMake = true;;
			}
		} else {

		}


	}
	IEnumerator chDir(){
		while (true) {
			if(!isShooting){
			moveDir = Random.Range(0,360);
			transform.Rotate  (0,0,moveDir);
			}
			yield return new WaitForSeconds(chTime);
			
		}
	}
}
