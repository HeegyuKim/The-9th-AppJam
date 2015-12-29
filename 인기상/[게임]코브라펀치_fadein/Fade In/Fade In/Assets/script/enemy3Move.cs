using UnityEngine;
using System.Collections;

public class enemy3Move : MonoBehaviour {
	bool isShooting;
	Sprite sprite;
	float moveDir;
	float chTime;
	
	public GameObject[] shooter = new GameObject[4];
	public GameObject bullet2;
	public float range= 500f;
	public float speed;
	public float turnSpeed = 360f;
	
	// Use this for initialization
	void Awake () {
		sprite = GetComponent<SpriteRenderer> ().sprite;
		chTime = Random.Range (1f,5f);
		StartCoroutine("shot");
		StartCoroutine("chDir");
	}
	// Update is called once per frame
	void FixedUpdate () {
		if (GM.isOver) {
			speed = 0;
			turnSpeed = 0;
			StopCoroutine("shot");
			StopCoroutine("chDir");
		}
		
		transform.Rotate(0,0,turnSpeed*Time.deltaTime * -1);

		if (!isShooting ) {
		Vector2 ori_p = transform.position;
		float xmove = Mathf.Cos(moveDir)*speed*Time.deltaTime;
		float ymove = Mathf.Sin(moveDir)*speed*Time.deltaTime;
		transform.localPosition = new Vector3(transform.localPosition.x+xmove,transform.localPosition.y+ymove,0);
			
			
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
		if (!isShooting) {
			if (Vector2.Distance (GameObject.Find ("player").transform.position, transform.position) < range) {
				isShooting = true;
			}
		} else {
			
		}
	}

	IEnumerator shot(){
		while (true) {
			if(isShooting){
				GameObject b;
				for(int i= 0 ; i<shooter.Length;i++){
					b= Instantiate(bullet2);
					b.transform.position = shooter[i].transform.position;
					b.transform.rotation = shooter[i].transform.rotation;
				}
			}
			yield return new WaitForSeconds(2f);
			
		}
	}


	IEnumerator chDir(){
		while (true) {
			moveDir = Random.Range(0,360);
			//transform.Rotate  (0,0,moveDir);
			yield return new WaitForSeconds(chTime);
			
		}
	}
}
