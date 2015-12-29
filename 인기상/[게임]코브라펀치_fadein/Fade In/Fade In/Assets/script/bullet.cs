using UnityEngine;
using System.Collections;

public class bullet : MonoBehaviour {

	public float speed = 800f;

	// Use this for initialization
	void Awake () {
		transform.localScale = new Vector2 (100,100);
		Destroy (gameObject, 4f);
	}
	
	// Update is called once per frame
	void Update () {
		if (GM.isOver)
			speed = 0;
		transform.Translate (Vector3.up * speed * Time.deltaTime);

	}
}
