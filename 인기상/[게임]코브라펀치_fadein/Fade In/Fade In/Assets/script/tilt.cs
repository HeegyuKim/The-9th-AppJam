using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class tilt : MonoBehaviour {
	Rigidbody2D rigidbody;
	Sprite sprite;
	public float speed = 1000f;
	public Text t;
	public bool waking = false;


	// Use this for initialization
	void Awake() {
		rigidbody = GetComponent<Rigidbody2D> ();
		sprite = GetComponent<SpriteRenderer> ().sprite;
	}

	void FixedUpdate(){
		if (GM.isOver) {
			rigidbody.velocity = Vector3.zero;
			return;
		}
		if (waking)
			rigidbody.velocity = Vector3.zero;
		else {
			Vector3 movement = new Vector3 (Input.acceleration.x, Input.acceleration.y, 0.0f);
			//t.text = movement.ToString();
			rigidbody.velocity = movement * speed;

			Vector2 moveDirection = rigidbody.velocity;
			float angle = Mathf.Atan2 (moveDirection.y, moveDirection.x) * Mathf.Rad2Deg;
			transform.rotation = Quaternion.AngleAxis (angle, Vector3.forward);
			transform.rotation = Quaternion.Euler (new Vector3 (transform.rotation.eulerAngles.x, transform.rotation.eulerAngles.y, transform.rotation.eulerAngles.z - 90f));

			if (transform.position.x < -2048f + sprite.rect.width / 2)
				transform.position = new Vector2 (-2048 + sprite.rect.width / 2, transform.position.y);
			if (transform.position.y < -2048f + sprite.rect.height / 2)
				transform.position = new Vector2 (transform.position.x, -2048 + sprite.rect.height / 2);
		
			if (transform.position.x > 2048f - sprite.rect.width / 2)
				transform.position = new Vector2 (2048 - sprite.rect.width / 2, transform.position.y);
			if (transform.position.y > 2048f - sprite.rect.height / 2)
				transform.position = new Vector2 (transform.position.x, 2048 - sprite.rect.height / 2);

			//transform.rotation = Quaternion.Euler (new Vector3 (Input.acceleration.x * 360, Input.acceleration.y * 360, 0.0f));


		}
	}
}
