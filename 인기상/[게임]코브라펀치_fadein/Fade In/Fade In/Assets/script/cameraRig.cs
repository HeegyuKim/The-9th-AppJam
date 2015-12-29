using UnityEngine;
using System.Collections;

public class cameraRig : MonoBehaviour {
	public Transform playerT;

	// Use this for initialization
	void Awake () {
	
	}
	
	// Update is called once per frame
	void LateUpdate () {
		transform.position = playerT.position;
		if (transform.position.x < -1568f)
			transform.position= new Vector2(-1568f,transform.position.y);
		if (transform.position.y < -1778f)
			transform.position =  new Vector2(transform.position.x,-1778f);

		if (transform.position.x > 1568f)
			transform.position= new Vector2(1568f,transform.position.y);
		if (transform.position.y > 1778f)
			transform.position =  new Vector2(transform.position.x,1778f);
	}
}
