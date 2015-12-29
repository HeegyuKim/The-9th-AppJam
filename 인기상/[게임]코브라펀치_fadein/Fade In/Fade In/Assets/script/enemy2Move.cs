using UnityEngine;
using System.Collections;

public class enemy2Move : MonoBehaviour {

	bool isShooting;
	Sprite sprite;
	float moveDir;
	float chTime;
	public GameObject[] shooter = new GameObject[3];
	public GameObject bullet1;
	public float range= 500f;
	
	public float speed;

	bool shoted = false;
	
	// Use this for initialization
	void Awake () {
		sprite = GetComponent<SpriteRenderer> ().sprite;
		chTime = Random.Range (1f,5f);
		StartCoroutine("chDir");
		StartCoroutine("shot");
	}
	// Update is called once per frame
	void FixedUpdate () {
		if (GM.isOver) {
			speed = 0;
			StopCoroutine("shot");
			StopCoroutine("chDir");
		}
		if (!isShooting || shoted) {
			
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
			}else{
				
				Vector2 ori_p = transform.position;
				//transform.Translate (Vector3.up * Time.deltaTime * speed);
				
				
				if (transform.position.x <= -2048f + sprite.rect.width) {
					transform.position = ori_p;
					transform.position = new Vector2 (transform.position.x + 2, transform.position.y);
					
				}
				if (transform.position.y <= -2048f + sprite.rect.height) {
					transform.position = ori_p;isShooting = false;
					transform.position = new Vector2 (transform.position.x, transform.position.y + 2);
				}
				
				if (transform.position.x >= 2048f - sprite.rect.width) {
					transform.position = ori_p;isShooting = false;
					transform.position = new Vector2 (transform.position.x - 2, transform.position.y);
				}
				if (transform.position.y >= 2048f - sprite.rect.height) {
					transform.position = ori_p;isShooting = false;
					transform.position = new Vector2 (transform.position.x, transform.position.y - 2);
				}
				
			}
		}
	}
	void Update(){
		if (!isShooting && !shoted) {
			if (Vector2.Distance (GameObject.Find ("player").transform.position, transform.position) < range) {
				isShooting = true;
				
				Vector3 vectorToTarget = GameObject.Find ("player").transform.position - transform.position;
				float angle = Mathf.Atan2(vectorToTarget.y, vectorToTarget.x) * Mathf.Rad2Deg;
				transform.rotation = new Quaternion(0,0,0,0);
				transform.Rotate (0, 0, angle-90f);
			}
		} else {
			
		}
		
		
	}
	IEnumerator shot(){
		while (true) {
			if(isShooting && shoted  ==false){
				GameObject b = Instantiate(bullet1);
				b.transform.position = shooter[0].transform.position;
				b.transform.rotation = shooter[0].transform.rotation;

				b = Instantiate(bullet1);
				b.transform.position = shooter[1].transform.position;
				b.transform.rotation = shooter[1].transform.rotation;

				b = Instantiate(bullet1);
				b.transform.position = shooter[2].transform.position;
				b.transform.rotation = shooter[2].transform.rotation;

				shoted = true;

				moveDir = Random.Range(0,360);
				transform.Rotate  (0,0,moveDir);
				isShooting =false;
			}
			else if(shoted==true){
				shoted = false;
			}
			yield return new WaitForSeconds(2);
			
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
