using UnityEngine;
using System.Collections;

public class starMove : MonoBehaviour {

	public float rotSpeed = 40f;

	// Use this for initialization
	void Awake () {

		rotSpeed += 20 * GM.stageLevel;
		rotSpeed = Random.Range (rotSpeed, rotSpeed + 40);
	}
	
	// Update is called once per frame
	void Update () {
		if (GM.isOver)
			return;
		transform.Rotate (-1*Vector3.forward* Time.deltaTime * rotSpeed);
	}
}
