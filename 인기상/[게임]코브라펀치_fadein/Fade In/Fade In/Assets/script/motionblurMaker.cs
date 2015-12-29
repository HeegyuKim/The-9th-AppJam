using UnityEngine;
using System.Collections;

public class motionblurMaker : MonoBehaviour {
	public GameObject mb;
	public float mbmakeSpeed = 0.1f;
	public float mbDestroySpeed = 0.2f;
	public bool doMake = true;

	// Use this for initialization
	void Awake () {

		StartCoroutine(make ());
	}
	
	// Update is called once per frame
	void Update () {
	if(GM.isOver)
			StopCoroutine("make");
	}

	IEnumerator make(){
		while (true) {
			yield return new WaitForSeconds (mbmakeSpeed);
			if(doMake){
			GameObject MB = Instantiate (mb);
			MB.GetComponent<motionblur>().doInit(transform.parent.gameObject,mbDestroySpeed);

			}
		}
	} 
}
